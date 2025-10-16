package org.koitharu.kotatsu.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import org.koitharu.kotatsu.core.db.entity.ChapterReadEntity

@Dao
abstract class ChapterReadDao {

	@Query("SELECT chapter_id FROM chapter_read WHERE manga_id = :mangaId")
	abstract suspend fun findReadChapterIds(mangaId: Long): Set<Long>

	@Query("SELECT chapter_id FROM chapter_read WHERE manga_id = :mangaId")
	abstract fun observeReadChapterIds(mangaId: Long): Flow<Set<Long>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun insert(entity: ChapterReadEntity)

	@Transaction
	open suspend fun markChapterAsRead(mangaId: Long, chapterId: Long) {
		insert(ChapterReadEntity(mangaId, chapterId, System.currentTimeMillis()))
	}

	@Transaction
	open suspend fun markChaptersAsRead(mangaId: Long, chapterIds: Collection<Long>) {
		val timestamp = System.currentTimeMillis()
		chapterIds.forEach { chapterId ->
			insert(ChapterReadEntity(mangaId, chapterId, timestamp))
		}
	}

	@Query("DELETE FROM chapter_read WHERE manga_id = :mangaId AND chapter_id = :chapterId")
	abstract suspend fun markChapterAsUnread(mangaId: Long, chapterId: Long)

	@Query("DELETE FROM chapter_read WHERE manga_id = :mangaId AND chapter_id IN (:chapterIds)")
	abstract suspend fun markChaptersAsUnread(mangaId: Long, chapterIds: Collection<Long>)

	@Query("DELETE FROM chapter_read WHERE manga_id = :mangaId")
	abstract suspend fun clearReadChapters(mangaId: Long)
}
