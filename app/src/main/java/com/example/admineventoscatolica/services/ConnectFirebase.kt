package com.example.admineventoscatolica.services

import com.example.admineventoscatolica.model.Events
import com.example.admineventoscatolica.model.Conference
import com.example.admineventoscatolica.model.Notice
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


fun ConnectFirebase(): DatabaseReference {
    return FirebaseDatabase.getInstance().getReference("catolica")
}

//// ----------------------------- EVENTOS -----------------------------

fun addEvent(event: Events) {
    val db = ConnectFirebase().child("eventos")
    val key = db.push().key ?: return
    db.child(key).setValue(event.copy(eventid = key))
}

fun updateEvent(event: Events) {
    val db = ConnectFirebase().child("eventos")
    event.eventid?.let {
        db.child(it).setValue(event)
    }
}

fun deleteEvent(eventId: String) {
    ConnectFirebase().child("eventos").child(eventId).removeValue()
}

//// ----------------------------- CONFERENCIAS -----------------------------

fun addConference(conference: Conference) {
    val db = ConnectFirebase().child("conferencias")
    val key = db.push().key ?: return
    db.child(key).setValue(conference.copy(id = key))
}

fun updateConference(conference: Conference) {
    val db = ConnectFirebase().child("conferencias")
    conference.id?.let {
        db.child(it).setValue(conference)
    }
}

fun deleteConference(conferenceId: String) {
    ConnectFirebase().child("conferencias").child(conferenceId).removeValue()
}
//// ----------------------------- NOTICIAS -----------------------------
fun addNotice(notice: Notice) {
    val db = ConnectFirebase().child("noticias")
    val key = db.push().key ?: return
    db.child(key).setValue(notice.copy(noticeid = key))
}

fun updateNotice(notice: Notice) {
    val db = ConnectFirebase().child("noticias")
    notice.noticeid?.let {
        db.child(it).setValue(notice)
    }
}

fun deleteNotice(noticeId: String) {
    ConnectFirebase().child("noticias").child(noticeId).removeValue()
}
