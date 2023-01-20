package com.diegulog.watchdog

class NativeLib {

    /**
     * A native method that is implemented by the 'watchdog' native library,
     * which is packaged with this application.
     */
    companion object {
        // Used to load the 'watchdog' library on application startup.
        init {
            System.loadLibrary("watchdog")
        }
    }
}