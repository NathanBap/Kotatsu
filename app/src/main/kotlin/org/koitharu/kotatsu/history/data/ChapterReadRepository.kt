package org.koitharu.kotatsu.history.data

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import org.koitharu.kotatsu.core.db.MangaDatabase
import javax.inject.Inject

@Reusable
class ChapterReadRepository @Inject constructor(
	private val db: MangaDatabase,
) {

	suspend fun getReadChapterIds(mangaId: Long): Set<Long> {
		return db.getChapterReadDao().findReadChapterIds(mangaId)
	}

	fun observeReadChapterIds(mangaId: Long): Flow<Set<Long>> {
		return db.getChapterReadDao().observeReadChapterIds(mangaId)
	}

	suspend fun markChapterAsRead(mangaId: Long, chapterId: Long) {
		db.getChapterReadDao().markChapterAsRead(mangaId, chapterId)
	}

	suspend fun markChaptersAsRead(mangaId: Long, chapterIds: Collection<Long>) {
		db.getChapterReadDao().markChaptersAsRead(mangaId, chapterIds)
	}

	suspend fun markChapterAsUnread(mangaId: Long, chapterId: Long) {
		db.getChapterReadDao().markChapterAsUnread(mangaId, chapterId)
	}

	suspend fun markChaptersAsUnread(mangaId: Long, chapterIds: Collection<Long>) {
		db.getChapterReadDao().markChaptersAsUnread(mangaId, chapterIds)
	}

	suspend fun clearReadChapters(mangaId: Long) {
		db.getChapterReadDao().clearReadChapters(mangaId)
	}
}
