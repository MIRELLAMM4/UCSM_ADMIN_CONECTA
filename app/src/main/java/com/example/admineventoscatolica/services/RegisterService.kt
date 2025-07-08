package com.example.eventoscatolica.services

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

object RegisterService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}