# cds-hooks
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5aa00a93841d4a6c8a8c25555de3f9a5)](https://www.codacy.com/manual/dhasilin/cds-hooks?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=HealthLX/cds-hooks&amp;utm_campaign=Badge_Grade)

Library that models [CDS Hooks v1 specification](https://cds-hooks.org/specification/1.0/).

Our goal is to provide nice and simple java model to simplify developers life.

## Getting Started
  * Just add dependency:

```groovy
dependencies {
	implementation 'com.healthlx.cdshooks:model:0.2'
}
```

## Usage

  * You are ready to go. Look for ```com.healthlx.cdshooks.model``` package for models.

  * Also you might want to register serializators/deserializators in your app.\
In case you are using Jackson we have made needed classes for you: ```com.healthlx.cdshooks.jackson```
In order to do so add dependency on jackson module
```groovy
dependencies {
	implementation 'com.healthlx.cdshooks:jackson:0.2'
}
```
With jackson module you don't need dependency on core cause it is bundled.

If you are using another serialization library you are welcome to contribute. 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
