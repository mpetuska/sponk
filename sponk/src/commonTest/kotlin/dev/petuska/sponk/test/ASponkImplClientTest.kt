package dev.petuska.sponk.test

import dev.petuska.sponk.Sponk
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class ASponkImplClientTest {
  private lateinit var config: ASponkClientConfig
  protected lateinit var sponk: Sponk
    private set
  protected val state: ASponkClientConfig.State get() = config.state

  @BeforeTest
  open fun beforeTest() {
    config = ASponkClientConfigLoader.load()
    sponk = Sponk.login(
      username = config.username,
      password = config.password,
      apiUrl = config.apiUrl,
      onLoadTokens = {
        config.state.bearerTokens
      },
      onRefreshTokens = {
        updateState(state.copy(bearerTokens = it))
      }
    )
  }

  protected suspend fun updateState(newState: ASponkClientConfig.State) {
    val newConfig = config.copy(state = newState)
    ASponkClientConfigLoader.save(newConfig)
    config = newConfig
  }

  @AfterTest
  open fun afterTest() {
    sponk.close()
  }
}
