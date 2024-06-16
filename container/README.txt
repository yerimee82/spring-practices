[src/main/java]
	com.poscodx.container.soundsystem
		|--- CDPlayer.java
		|--- CompactDisc.java

	com.poscodx.container.videosystem
		|--- DVDPlayer.java
		|--- DigitalVideoDisc.java
		|--- Avengers

	com.poscodx.container.config.soundsystem
		|--- CDPlayerConfig.java

	com.poscodx.container.config.videosystem
		|--- DVDPlayerConfig.java

	com.poscodx.container.config.videosystem.mixing
		|--- DVDConfig.java
		|--- DVDPlayerConfig.java
		|--- VideoSystemConfig.java

[src/main/resources]
	com.poscodx.container.config.soundsystem
		|--- applicationContext.xml

	com.poscodx.container.config.videosystem
		|--- applicationContext.xml

[src/test/java]
	com.poscodx.container.soundsystem
		|--- CDPlayerXmlConfigTest.java
		|--- CDPlayerJavaConfigTest.java

	com.poscodx.container.videosystem
		|--- DVDPlayerJavaConfigTest.java
		|--- DVDPlayerXmlConfigTest.java

	com.poscodx.container.config.videosystem.mixing
		|--- DVDPlayerMixingConfigTest01.java
		|--- DVDPlayerMixingConfigTest02.java
