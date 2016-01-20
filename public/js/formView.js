var Backbone = require('backbone');
var $ = require('jquery');
Backbone.$ = $;
var _ = require('underscore');
var tmpl = require('./templates');
var DrinkCollection = require('./collection');
var DrinkCollectionView = require('./collectionView');

module.exports = Backbone.View.extend({
  el: '.form',
  events: {
    'click .send-stuff': 'onSubmitIngredients',
    'click .like': 'onLike'
  },
  initialize: function () {
  },
  onSubmitIngredients: function(event){
    event.preventDefault();
    $('.drinkList').html("");
    var drinkCollection = new DrinkCollection();
    drinkCollection.fetch().then(function(data) {
      // initialize a new array for the filtered data
      var newData = [];
      // sort through the objects within the array
        for (var i = 0; i < data.length; i++) {
          // sort through the keys within the objects and set nulls to empty strings
          // otherwise the templates would break
          // thanks Charles for this type of loop
          for(var prop in data[i]){
            if (data[i][prop] == null){
              data[i][prop] = "";
            }
          }
          var combinedIngredients = '';
          // set input value and ingredient values to uppercase and compare
          // if it matches, push it to the new data array
       var ingredient1 = data[i].ingredient1.toUpperCase();
       var ingredient2 = data[i].ingredient2.toUpperCase();
       var ingredient3 = data[i].ingredient3.toUpperCase();
       var ingredient4 = data[i].ingredient4.toUpperCase();
       var ingredient5 = data[i].ingredient5.toUpperCase();
       var ingredient6 = data[i].ingredient6.toUpperCase();
       var ingredient7 = data[i].ingredient7.toUpperCase();
       var ingredient8 = data[i].ingredient8.toUpperCase();
       var ingredient9 = data[i].ingredient9.toUpperCase();
       var ingredient10 = data[i].ingredient10.toUpperCase();
       var ingredient11 = data[i].ingredient11.toUpperCase();
       var ingredient12 = data[i].ingredient12.toUpperCase();
       combinedIngredients += ingredient1;
       combinedIngredients += ingredient2;
       combinedIngredients += ingredient3;
       combinedIngredients += ingredient4;
       combinedIngredients += ingredient5;
       combinedIngredients += ingredient6;
       combinedIngredients += ingredient7;
       combinedIngredients += ingredient8;
       combinedIngredients += ingredient9;
       combinedIngredients += ingredient10;
       combinedIngredients += ingredient11;
       combinedIngredients += ingredient12;

       /// SETS ALL UPPER CASE//
       var ingredientOneID = $('#ingredientOne').val().toUpperCase();
       var ingredientTwoID = $('#ingredientTwo').val().toUpperCase();
       var ingredientThreeID = $('#ingredientThree').val().toUpperCase();
       var ingredientFourID = $('#ingredientFour').val().toUpperCase();

       if(
         combinedIngredients.includes(ingredientOneID)&&
         combinedIngredients.includes(ingredientTwoID)&&
         combinedIngredients.includes(ingredientThreeID)&&
         combinedIngredients.includes(ingredientFourID)
       ) {
         newData.push(data[i]);
       }
     }
        return newData;
    }).then(function(newData) {
      // sets the collection to the filtered data
      // thanks McGarnagie for the tip on reset @ http://stackoverflow.com/questions/13519515/backbonejs-collection-reset-vs-collection-fetch
        drinkCollection.reset(newData);
        new DrinkCollectionView({collection: drinkCollection});
      });
  },
  template: _.template(tmpl.form),
  render: function () {
    var markup = this.template({});
    this.$el.html(markup);
    return this;
  }
});
