package com.example.kotkit.data.localstorage

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import com.example.kotkit.data.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@UnstableApi
class VideoCacheManager(context: Context) {
    private val cacheDir = File(context.cacheDir, "video_cache")
    private val databaseProvider = StandaloneDatabaseProvider(context)

    private val cache = SimpleCache(
        cacheDir,
        LeastRecentlyUsedCacheEvictor(100 * 1024 * 1024), // 100MB cache
        databaseProvider
    )

    private val preloadQueue = mutableListOf<Video>()
    private var isPreloading = false

    private val cachedVideoQueue = ArrayDeque<Int>() // videoId queue
    private val maxCachedVideos = 5


    fun getCacheDataSourceFactory(context: Context): CacheDataSource.Factory {
        val dataSource = DefaultDataSource.Factory(context)

        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(dataSource)
            .setCacheWriteDataSinkFactory(null)
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }

    private fun isVideoCached(videoId: Int): Boolean {
        return cachedVideoQueue.contains(videoId)
    }

    fun clearCache() {
        preloadQueue.clear()
        cachedVideoQueue.clear()
        cache.release()
    }
}