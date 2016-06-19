# Encoder-Based Turning
NOTE:  I (Jamie) learned this method from Kian Sheik, our 2014-2015 Head of Controls and a programmer on De-Evolution.  I don't know if he came up with it or got it somewhere else, but now I will teach it to you.  This is how the autonomous driving code currently works on this team. 

It's really fairly simple; it just uses geometry.

With encoders on both sides of the drivetrain, you can know the angular distance each wheel has traveled since the encoders were last reset.
And if you know the circumference of the wheels, you can convert that to linear distance.

Like you learned in Algebra 2 Trig, or whatever integrated class, it's just `d = CA/360`
(C = circumference, A = angle)

Or, if you use radians (which we don't), `d = rθ`

Using that, you can figure out how far you have moved from your starting position.
Going forwards and backwards is simple.  You just have to calculate how far you have traveled, and stop when you've gone far enough.

##### The in-place turn

Turning is a little harder. 
Consider this robot.

![Explanation Image 1](https://github.com/Team3128/info/raw/master/resources/images/turning-explanation-1.png
)

This is a tank drive robot with four wheels.

We can draw a circle which contacts all four wheels where they touch the ground.  I call this the turning circle.

Now, let's say the robot turns 90 degrees counterclockwise.
![Explanation Image 2](https://github.com/Team3128/info/raw/master/resources/images/turning-explanation-2.png)

The neat thing about this diagram is that during this turn the wheel moves exactly 90 degrees on this circle.  So the **distance** the wheel travels is exactly 1/4 the circumference of the circle.  Any turn angle can be converted to a fraction of this circumference.

###### When turning the robot:

If the diameter of the turning circle (the diagonal distance from the front left wheel to the right back wheel) is **W**

And the wheel circumference is **C**

And the angular distance you want the robot to turn is **D**

The linear distance that the wheel has to move is just the fraction of the turning circle circumference corresponding to die distance you want to turn, `DWπ/360`

We substitute the angular motion equation: `DWπ/360 = CA/360`

`DWπ = CA`

So the angular distance that the wheel has to turn is `A = DWπ/C`


This equation is how the code calculates in-place ("tank") turning.  This model of turning is pretty good, but it's not perfect.  It is reasonably accurate as long as the wheelbase value is correct (try adjusting this value if the the angles it turns are wrong). It works less well when the floor isn't slippery, because the wheels have to slip sideways in order to turn, and the model ignores this friction.  For example, it works REALLY bad on the concrete outside the FRC room when the robot has grippy rubber (Colson) wheels.  However, it works fine on carpet and decent on the FTC/VEX floor tiles.

There is, however, another method of turning, which is sometimes superior.

##### Arc turning

In this method of turning, you stop the wheels completely on one side of the bot and run them on the other side. Arc turning is generally more accurate than in-place turning, because one side moves twice as far so you need less fine-grain distance control.

Now, this method only works reliably  when you use omni wheels (the wheels with sideways rollers which can slip sideways) on the front OR back (but not both) of your robot.  We had this on the De-Evolution robot in 2015.  This is so that the pivot point of the turning circle is at one of the wheels, instead of somwhere in between.

  For our example robot, let's say the roller wheels are on the back.  If we arc turn left, we stop the left wheels and run the right ones forward.  The stopped left wheel in the front becomes the center of the turning circle. 

![Explanation Image 3](https://github.com/Team3128/info/raw/master/resources/images/turning-explanation-3.png)

This time, the turning circle radius is the distance across the robot, from a left wheel to a right wheel.  This distance is known as the track.

This time, if we let **T** be the track, **D** be the desired turning angle, and **C** be the wheel circumference, 

By the same logic as earlier, we need to turn a fraction of the turning circle circumference, so the linear distance that the right wheels move is `2πDT/360`

`CA/360 = 2πDT/360`

`CA = 2πDT`

So, the angular distance that the right wheels turn is `2πDT/C` This, again, is the formula the code uses to make arc turns.

For reference, [here](https://github.com/De-Evolution/ftc-team-4278/blob/master/drivers/autoutils.h) is the original implementation on De-Evolution


And yeah, I realize that code in the link is a bit of a mess, I didn't write it.  Please don't code like that, please.
 
 