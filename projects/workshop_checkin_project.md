# Workshop Checkin Project
During the 2015-2016 year, a number of issues were prompted with the use of the attendance scanner which we hope to rectify. The main issues were:

- Scanner tablet was not always available (battery died, etc)
- Scanner was located a decent way into the workshop.
- Scanner logs had to be manually processed and pushed to Github
- Scanner app not easily hackable due to its reliance on the Android API

We aim to fix these problems by:

- Moving to a device that can operate continuously.
- Moving to a device where more scripting and computing is accessible.
- Automatically processing/uploading attendance data to Github

## Proposed Solution

Our hope is to use a Raspberry Pi connected to a USB Scanner and Keypad to solve the above solutions. By using a Raspberry Pi as the base device, we expect to gain a number of advantages:

- Runs Linux, which means most scripts, libraries, etc are available to the developer making it easier for students to hack and for automated processes to occur.
- Plugs directly into the wall, making it always available.
- Has both Bluetooth and Wifi connectivity, which enables it to upload processed data.
- Has multiple USB ports, enabling for several devices to be connected such as both a Scanner and Keypad, allowing for multiple types of inputs.

### Action Steps

- [ ] Purchase Raspberry Pi
- [ ] Perform Code/Process Analysis on Attendance Scanner App
- [ ] Rewrite Attendance Scanner to work on linux
- [ ] Test Attendance Scanner on Raspberry Pi
- [ ] Automate attendance script processing/uploading
- [ ] Install into scanning station at workshop door
- [ ] Review feedback after one month


