package org.koitharu.kotatsu.core.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration27To28 : Migration(27, 28) {

	override fun migrate(db: SupportSQLiteDatabase) {
		db.execSQL(
			"""CREATE TABLE IF NOT EXISTS chapter_read (
				manga_id INTEGER NOT NULL,
				chapter_id INTEGER NOT NULL,
				read_at INTEGER NOT NULL,
				PRIMARY KEY(manga_id, chapter_id),
				FOREIGN KEY(manga_id) REFERENCES manga(manga_id) ON DELETE CASCADE
			)"""
		)
		db.execSQL("CREATE INDEX IF NOT EXISTS index_chapter_read_manga_id ON chapter_read(manga_id)")
	}
}
