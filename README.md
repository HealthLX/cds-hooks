# cds-hooks

Library that models [CDS Hooks v1 specification](https://cds-hooks.org/specification/1.0/).

Our goal is to provide nice and simple java model to simplify developers life.

## Getting Started
* Just add dependency:

At the moment we are working on publishing to maven central. So for now please use [JitPack](https://www.jitpack.io):

```groovy
repositories {
    maven { url 'https://www.jitpack.io' }
}
dependencies {
	        implementation 'com.github.HealthLX:cds-hooks:0.1.0'
}
```

## Usage

* You are ready to go. Look for ```com.healthlx.cdshooks.model``` package for models.

* Also you might want to register serializators/deserializators in your app.\
In case you are using Jackson we have made needed classes for you: ```com.healthlx.cdshooks.jackson```
If you are using another serialization library you are welcome to contribute. 

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
This project is licensed under the Apache 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
