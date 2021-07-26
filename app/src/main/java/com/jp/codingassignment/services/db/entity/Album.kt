package com.jp.codingassignment.services.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shrinivas Pai on 23/07/21.
 */
@Entity
data class Album(@PrimaryKey
                    var id: Long,
                    var title: String,
                    var userId: Long) {
}