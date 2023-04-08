package com.github.johnnysc.numberstesttask.main.presentation

import kotlinx.coroutines.Job

/**
 * @author Asatryan on 08.04.2023
 */
interface UiFeature {

    fun handle(handle: Handle): Job
}