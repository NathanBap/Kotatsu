package org.koitharu.kotatsu.core.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import org.koitharu.kotatsu.core.db.TABLE_CHAPTER_READ

@Entity(
	tableName = TABLE_CHAPTER_READ,
	primaryKeys = ["manga_id", "chapter_id"],
	foreignKeys = [
		ForeignKey(
			entity = MangaEntity::class,
			parentColumns = ["manga_id"],
			childColumns = ["manga_id"],
			onDelete = ForeignKey.CASCADE,
		),
	],
	indices = [
		Index(value = ["manga_id"]),
	],
)
data class ChapterReadEntity(
	@ColumnInfo(name = "manga_id") val mangaId: Long,
	@ColumnInfo(name = "chapter_id") val chapterId: Long,
	@ColumnInfo(name = "read_at") val readAt: Long,
)
