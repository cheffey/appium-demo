## Step by Step test run
### 1. install appium
run shell:  **npm -g install appium@1.19.1**
### 2. start appium
run shell and keep it open: **appium**
### 3. connect the meetingBar A30 through adb(remote or local)
check uuid with shell: **adb devices**

For example the result:
```
chef.xie@LM2DQMD6M ~ % adb devices
List of devices attached
10.32.39.38:5555	device
10.32.39.39:5555	device
```
### 4. Modify test code
Modify HostDemo.kt fragment with uuid that just found: **caps.setCapability("uuid", "10.32.39.38:5555")**
### 5. Run Demo test
There are 5 simple tests that could test 5 major API our test framework commonly use.

### Warning:
Please make sure RCV Rooms is already setup in the device. And the test can only be used for the host(meetingBarA30), NOT the controller(CTP-18)