# LhHotFix
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.13652900200:LhHotFix:v1.0'
	}
