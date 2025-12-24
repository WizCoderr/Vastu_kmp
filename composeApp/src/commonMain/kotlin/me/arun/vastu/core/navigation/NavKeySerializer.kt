package me.arun.vastu.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.serializer

object NavKeySerializer : KSerializer<NavKey> {
    private val delegate = serializer<AppScreen>()
    override val descriptor: SerialDescriptor = delegate.descriptor

    override fun deserialize(decoder: Decoder): NavKey {
        return delegate.deserialize(decoder)
    }

    override fun serialize(encoder: Encoder, value: NavKey) {
        delegate.serialize(encoder, value as AppScreen)
    }
}
