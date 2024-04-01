package dev.petuska.sponk.data.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AutocompleteLocation(
  @SerialName("featureName")
  var featureName: String,
  @SerialName("addressLine")
  var addressLine: String,
  @SerialName("placeId")
  var placeId: LocationId,
)

typealias LocationId = String

@Serializable
data class Location(
  @SerialName("addressLine")
  var addressLine: String? = null,
  @SerialName("address")
  var address: String? = null,
  @SerialName("administrativeAreaLevel1")
  var administrativeAreaLevel1: String? = null,
  @SerialName("administrativeAreaLevel2")
  var administrativeAreaLevel2: String? = null,
  @SerialName("country")
  var country: String? = null,
  @SerialName("featureName")
  var featureName: String? = null,
  @SerialName("feature")
  var feature: String? = null,
  @SerialName("id")
  var id: LocationId? = null,
  @SerialName("latitude")
  var latitude: Double? = null,
  @SerialName("locality")
  var locality: String? = null,
  @SerialName("longitude")
  var longitude: Double? = null,
  @SerialName("postalCode")
  var postalCode: String? = null,
) {
  fun expand() = copy(
    feature = feature ?: featureName,
    featureName = featureName ?: feature,
    address = address ?: addressLine,
    addressLine = addressLine ?: address,
  )
}
