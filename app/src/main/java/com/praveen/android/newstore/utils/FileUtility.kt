package com.praveen.android.newstore.utils

import android.content.Context
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class FileUtility {

    companion object {

        fun create(
            context: Context,
            fileName: String,
            jsonString: String?
        ): Boolean {
            return try {
                val fos: FileOutputStream =
                    context.openFileOutput(fileName, Context.MODE_PRIVATE)
                if (jsonString != null) {
                    fos.write(jsonString.toByteArray())
                }
                fos.close()
                true
            } catch (fileNotFound: FileNotFoundException) {
                false
            } catch (ioException: IOException) {
                false
            }
        }
    }
}