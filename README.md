# theGravITy
Test task for 'The GravITy Agency'. Automated tests for the Web form- https://goo.gl/forms/t16Uov7ZHXCrB2ZE2

Required:
- Windowslatform
- Chrome browser
- Java and Maven should be installed and difined to the PATH environment variable: 
     https://warwick.ac.uk/fac/sci/dcs/people/research/csrcbc/teaching/howto/javapath/
     https://www.guru99.com/maven-jenkins-with-selenium-complete-tutorial.html
 


1) Download/clone project to your local workspace.
2) In folder 'selenium tools' run "run_server_standalone.bat". 
3) Then run maven console with command 'mvn clean test -Dbrowser=CHROME -Dplatform=WINDOWS'

Tests will run in 4 threads in Chrome browser (now only Chrome browser on Windows OS is availabe)
