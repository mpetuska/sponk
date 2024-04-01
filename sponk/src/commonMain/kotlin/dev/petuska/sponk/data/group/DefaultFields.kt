package dev.petuska.sponk.data.group

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultFields(
  @SerialName("field_member_address")
  var fieldMemberAddress: FieldMemberAddress,
  @SerialName("field_member_dob")
  var fieldMemberDob: FieldMemberDob,
  @SerialName("field_member_guardian")
  var fieldMemberGuardian: FieldMemberGuardian
)
