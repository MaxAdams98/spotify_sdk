package de.minimalme.spotify_sdk

import com.google.gson.Gson
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.protocol.types.PlayerState
import com.spotify.protocol.types.PlayerRestrictions
import com.spotify.protocol.types.Track
import com.spotify.protocol.types.Album
import com.spotify.protocol.types.Artist
import com.spotify.protocol.types.ImageUri
import io.flutter.plugin.common.MethodChannel

class SpotifyPlayerApi(spotifyAppRemote: SpotifyAppRemote?, result: MethodChannel.Result) : BaseSpotifyApi(spotifyAppRemote, result) {


    private val errorCrossfadeState = "crossfadeStateError"
    private val errorPlayerState = "PlayerStateError"
    private val errorQue = "queueError"
    private val errorPlay = "playError"
    private val errorPause = "pauseError"
    private val errorResume = "resumeError"
    private val errorSkipNext = "skipNextError"
    private val errorSkipPrevious = "skipPreviousError"
    private val errorSeekTo = "seekToError"
    private val errorSkipToIndex = "skipToIndexError"
    private val errorPodcastPlaybackSpeed = "podcastPlaybackSpeedError"
    private val errorToggleShuffle = "toggleShuffleError"
    private val errorToggleRepeat = "toggleRepeatError"

    private val playerApi = spotifyAppRemote?.playerApi

    internal fun getCrossfadeState() {
        if (playerApi != null) {
            playerApi.crossfadeState
                    .setResultCallback { crossfadeState ->
                        result.success(Gson().toJson(crossfadeState))
                    }
                    .setErrorCallback { throwable -> result.error(errorCrossfadeState, "error when getting the current state of crossfade setting", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun getPlayerState() {
        if (playerApi != null) {
            playerApi.playerState
                    .setResultCallback { playerState ->
                        result.success(Gson().toJson(playerState))

                    }
                    .setErrorCallback { throwable -> result.error(errorPlayerState, "error when getting the current state of the player", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun queue(spotifyUri: String?) {
        if (playerApi != null && !spotifyUri.isNullOrBlank()) {
            playerApi.queue(spotifyUri)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorQue, "error when adding uri: $spotifyUri to queue", throwable.toString()) }
        } else if (spotifyUri.isNullOrBlank()) {
            result.error(errorQue, "spotifyUri has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun play(spotifyUri: String?) {
        if (playerApi != null && !spotifyUri.isNullOrBlank()) {
            playerApi.play(spotifyUri)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorPlay, "error when playing uri: $spotifyUri", throwable.toString()) }
        } else if (spotifyUri.isNullOrBlank()) {
            result.error(errorPlay, "spotifyUri has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun pause() {
        if (playerApi != null) {
            playerApi.pause()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorPause, "error when pausing", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun resume() {
        if (playerApi != null) {
            playerApi.resume()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorResume, "error when resuming", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun seekTo(positionedMilliseconds: Int?) {
        if (playerApi != null && positionedMilliseconds != null) {
            playerApi.seekTo(positionedMilliseconds.toLong())
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSeekTo, "error when seeking to position", throwable.toString()) }
        } else if (positionedMilliseconds == null) {
            result.error(errorSeekTo, "positionedMilliseconds has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun seekToRelativePosition(relativeMilliseconds: Int?) {
        if (playerApi != null && relativeMilliseconds != null) {
            playerApi.seekToRelativePosition(relativeMilliseconds.toLong())
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSeekToRelativePosition, "error when seeking to relative position", throwable.toString()) }
        } else if (relativeMilliseconds == null) {
            result.error(errorSeekToRelativePosition, "relativeMilliseconds has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun setPodcastPlaybackSpeed(podcastPlaybackSpeed: Float?) {
        if (playerApi != null && podcastPlaybackSpeed != null) {
            playerApi.setPodcastPlaybackSpeed(podcastPlaybackSpeed)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSetPodcastPlaybackSpeed, "error when setting podcast playback speed", throwable.toString()) }
        } else if (podcastPlaybackSpeed == null) {
            result.error(errorSetPodcastPlaybackSpeed, "podcastPlaybackSpeed has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun skipNext() {
        if (playerApi != null) {
            playerApi.skipNext()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSkipNext, "error when skipping to next track", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun skipPrevious() {
        if (playerApi != null) {
            playerApi.skipPrevious()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSkipPrevious, "error when skipping to previous track", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun skipToIndex(uri: String?, index: Int?) {
        if (playerApi != null && !uri.isNullOrBlank() && index != null) {
            playerApi.skipToIndex(uri, index)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorSkipToIndex, "error when skipping to index", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun toggleShuffle() {
        if (playerApi != null) {
            playerApi.toggleShuffle()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorToggleShuffle, "error when toggle shuffle", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun setShuffle(shuffle: Boolean?) {
        if (playerApi != null && shuffle != null) {
            playerApi.setShuffle(shuffle)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorToggleRepeat, "error when toggle shuffle", throwable.toString()) }
        } else if (shuffle == null) {
            result.error(errorQue, "shuffle has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun toggleRepeat() {
        if (playerApi != null) {
            playerApi.toggleRepeat()
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorToggleRepeat, "error when toggle repeat", throwable.toString()) }
        } else {
            spotifyRemoteAppNotSetError()
        }
    }

    internal fun setRepeatMode(repeatMode: Int?) {
        if (playerApi != null && repeatMode != null) {
            playerApi.setRepeat(repeatMode)
                    .setResultCallback { result.success(true) }
                    .setErrorCallback { throwable -> result.error(errorToggleRepeat, "error when toggle repeat", throwable.toString()) }
        } else if (repeatMode == null) {
            result.error(errorQue, "repeatMode has invalid format or is not set", "")
        } else {
            spotifyRemoteAppNotSetError()
        }
    }
}
