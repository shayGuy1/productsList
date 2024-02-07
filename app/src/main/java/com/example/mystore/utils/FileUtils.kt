package com.example.mystore.utils

import com.example.mystore.api.data.ProductsResponse
import com.example.mystore.application.App
import com.google.gson.Gson
import java.io.*

object FileUtils {

    fun saveProductsToStorage(json: String) {
        val yourFilePath: String = App.getAppContext().filesDir.toString() + "/products_data_file"
        val yourFile = File(yourFilePath)
        if (yourFile.exists())
            yourFile.delete()
        val fileOutputStream = FileOutputStream(yourFile)
        fileOutputStream.write(json.toByteArray())
        fileOutputStream.flush()
        fileOutputStream.close()
    }

    fun loadProductsFromStorage(): ProductsResponse? {
        val text: String
        try {
            val yourFilePath: String = App.getAppContext().filesDir.toString() + "/products_data_file"
            val yourFile = File(yourFilePath)
            val inputStream: InputStream = FileInputStream(yourFile)
            val stringBuilder = StringBuilder()
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String?
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append(receiveString)
            }
            inputStream.close()
            text = stringBuilder.toString()
        } catch (e: Exception) {
            return null
        }
        return Gson().fromJson(text, ProductsResponse::class.java)
    }

}