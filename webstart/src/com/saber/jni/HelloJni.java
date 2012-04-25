package com.saber.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class HelloJni {
/*    public native void GetMacNo();

    static {
        System.loadLibrary("COMM232");
    }

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.library.path"));
        new HelloJni().GetMacNo();
    }*/
	
	// This is the standard, stable way of mapping, which supports extensive
    // customization and mapping of Java to native types.

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.loadLibrary("COMM232", CLibrary.class);
        void GetMacNo();
    }

    public static void main(String[] args) {
    	System.out.println(System.getProperty("java.library.path"));
    	int[] macNo = null;
        CLibrary.INSTANCE.GetMacNo();
    }
}
