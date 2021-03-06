<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/normalize.css">


    <link rel="stylesheet" href="css/style.css">


</head>

<body>

<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <h1>Sign Up for Free</h1>

            <form action="/signup" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" name="firstName" required autocomplete="off"/>
                        <input hidden type="text" name="action" autocomplete="off" value="signup"/>

                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input name="lastName" type="text" required autocomplete="off"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input name="email" type="email" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input name="password" type="password" required autocomplete="off"/>
                </div>

                <% if (request.getAttribute("error") != null) { %>
                    <div class="field-wrap" style=" color: lightyellow;text-align: center;font-size: 1.5em;">
                        <b>
                            **  <%= request.getAttribute("error")%> try again
                        </b>
                    </div>
                <% } %>
                <button type="submit" class="button button-block"/>
                Get Started</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form action="/signup" method="post">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" name="email" required autocomplete="off"/>
                </div>
                <input hidden type="text" name="action" autocomplete="off" value="login"/>
                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name="pass" required autocomplete="off"/>
                </div>

                <p class="forgot"><a href="#">Forgot Password?</a></p>

                <input type="submit" class="button button-block" value="login"/>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>


</body>
</html>
