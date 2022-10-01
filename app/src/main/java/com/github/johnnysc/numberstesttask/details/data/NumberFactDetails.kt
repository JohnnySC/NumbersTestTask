package com.github.johnnysc.numberstesttask.details.data

/**
 * @author Asatryan on 01.10.2022
 */
interface NumberFactDetails {
    interface Save {
        fun save(data: String)
    }

    interface Read {
        fun read(): String
    }

    interface Mutable : Save, Read

    class Base : Mutable {

        private var value = ""

        override fun save(data: String) {
            value = data
        }

        override fun read() = value
    }
}