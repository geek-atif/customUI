package com.network.module.data

import kotlinx.coroutines.Job
/**
 * Created by Atif Qamar
 */
interface NetworkData {
   fun fetchCustomUI():Job
}