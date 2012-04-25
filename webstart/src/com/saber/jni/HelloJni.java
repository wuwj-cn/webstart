package com.saber.jni;

public class HelloJni {
    public native void GetMacNo();

    static {
        System.loadLibrary("COMM232");
    }

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.library.path"));
        new HelloJni().GetMacNo();
    }
}
