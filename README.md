# Sponk

KMP client for unpublished [Spond](https://spond.com) API.

> DISCLAIMER: This project has bare minimum API coverage for my personal needs.
> Feel free to use the code as you wish,
> but I have no plans to publish it or maintain it.

Heavily inspired by prior work at https://github.com/Olen/Spond.

## Development Setup

1. Create an account at https://spond.com/landing/register
2. Verify your email
3. Login at https://spond.com/landing/login
4. Open browser dev tools network tab
5. Filter by `XHR` requests only
6. Filter by url `/core/v1`
7. Create `sponk.config.json` file at the project root with the following contents

```json5
{
  "username": "<email>",
  "password": "<password>",
  // Optional
  "groupId": "9AA7C006C4C44E4CA70C5025A5BDB1AE"
}
```

8. Run various actions
   in [./sponk/src/commonTest/kotlin/dev/petuska/sponk/SponkTest.kt](./sponk/src/commonTest/kotlin/dev/petuska/sponk/SponkTest.kt)
