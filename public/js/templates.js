module.exports = {

  profile: [
    "<div class='profile'>",
    "<ul>",
    "<li><img src='http://www.fillmurray.com/460/300' /></li>",
    "<li><h2>User Name</h2></li>",
    "</ul>",
    // "<h3>Favorite Recipes</h3>",
    "<section id='recipes'>",
    "</section>",
    "</div>"
  ].join(''),
  favorites: [
    "<article>",
      "<h3><%=drink.name%></h3>",
      "<ul id='drink.ingredientList'>",
        "<li><%=drink.ingredient1%></li>",
        "<li><%=drink.ingredient2%></li>",
        "<li><%=drink.ingredient3%></li>",
        "<li><%=drink.ingredient4%></li>",
        "<li><%=drink.ingredient5%></li>",
        "<li><%=drink.ingredient6%></li>",
        "<li><%=drink.ingredient7%></li>",
        "<li><%=drink.ingredient8%></li>",
        "<li><%=drink.ingredient9%></li>",
        "<li><%=drink.ingredient10%></li>",
        "<li><%=drink.ingredient11%></li>",
        "<li><%=drink.ingredient12%></li>",
      "</ul>",
    "</article>",
  ].join(''),
  navigation: [
    "<ul id='nav'>",
      "<li><h1>alcoh<img src='https://d30y9cdsu7xlg0.cloudfront.net/png/76022-200.png'>me</h1></li>",
      "<li><a id='home' href='#home'>Home</a></li>",
      "<li><a id='profile' href='#profile'>My Profile</a></li>",
      "<li><a id='logout' href='#'>Logout</a></li>",
    "</ul>"
  ].join(""),
  form: [
    "<form class='drinkform'>",
     "<input type='text' id='ingredientOne' class='ingredients' placeholder='optional ingredients'>",
     "<input type='text' id='ingredientTwo' class='ingredients' placeholder='optional ingredients' >",
     "<input type='text' id='ingredientThree' class='ingredients' placeholder='optional ingredients'>",
     "<input type='text' id='ingredientFour' class='ingredients' placeholder='optional ingredients'>",
     "<button class='send-stuff'>submit</button>",
   "</form>"
 ].join(""),
 sideUser: [

   "<li>",
   "<img src='<%= image %>' />",
   "<h5><%= username %></h5>",

 ].join(""),
 recipe: [
   "<h3><%=name%></h3>",
   "<ul class='ingredientList'>",
   "<li><%=ingredient1%></li>",
   "<li><%=ingredient2%></li>",
   "<li><%=ingredient3%></li>",
   "<li><%=ingredient4%></li>",
   "<li><%=ingredient5%></li>",
   "<li><%=ingredient6%></li>",
   "<li><%=ingredient7%></li>",
   "<li><%=ingredient8%></li>",
   "<li><%=ingredient9%></li>",
   "<li><%=ingredient10%></li>",
   "<li><%=ingredient11%></li>",
   "<li><%=ingredient12%></li>",
   "</ul>",
   "<button class='like'>I'd Drink That!</button>",
 ].join(""),
 ingredient: [
   "<li>Ingredient Name</li>"
 ].join(""),
 login:[
   "<div class='box'>",
   "<h1 class='title'>alcoh<img src='https://d30y9cdsu7xlg0.cloudfront.net/png/76022-200.png'>me</h1></i>",
   "<input class='username' placeholder='username'></input>",
   "<input type='password' class='password initialPassword' placeholder='password'></input>",
   "<input type='password' class='password confirmPassword hidden' placeholder='confirm password'></input>",
   "<input class='photo hidden' placeholder='photo'></input>",
   "<li><a id='submit' href='#home'>SUBMIT</a></li>",
   "<li><a id='submitCreate' class='hidden' href='#home'>SUBMIT</a></li>",
   "<li><a id='cancel' class='hidden' href=''>cancel</a></li>",
   "<li class='createUser'><button>Not a user? Create an account!</button></li>",
   "</div>"
 ].join(""),
littleUserView: [
  "<h6><%= username %></h6>"
].join("")
};
