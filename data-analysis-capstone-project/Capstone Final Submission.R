library(lubridate)
library(sjmisc)

#Run this function to find daily sales!
finalFunction("Friday", "12/16/22", 34.1, 27.9, "Clear")


#Parameters: 
  #1. Day of week
      #Type: Character 
      #Range: Monday-Sunday
      #Examples: "Friday", "Monday"

  #2. Date
      #Type: Character 
      #Range: Any day
      #Format: mm/dd/yy
      #Examples: "12/16/22", "07/02/23"

  #3. Weather High
      #Type: Numeric
      #Range: 5-100
      #Examples: 34.1, 70, 92.8

  #4. Weather Low
      #Type: Numeric 
      #Range: -5-90
      #Examples: 32, -1.2, 52,8

  #5. Weather Conditions
      #Type: Character 
      #Possible Values: Clear, Partially cloudy, Overcast, Rain, Snow, Ice, Freezing Drizzle/Freezing Rain
      #(These are case-sensitive!!)
      #Examples: "Clear", "Rain, Snow, Partially cloudy", "Overcast"





#Model for total daily sales
quadraticModel = lm(`Sales.Returns` ~ Date + Date2 + High + Date*High + Low + Date*Low + 
                                      High*Low + DOW + Clouds + Rain + Winter, data=trainSet)

#Model for ice cream daily sales
iceCreamModel = lm(`Sales.Returns` ~ Date + Date2 + High + Date*High + Low + Date*Low + 
                                     High*Low + DOW + Clouds + Rain + Winter, data=iceCreamTrain)

#Model for food daily sales
foodModel = lm(`Sales.Returns` ~ Date + Date2 + High + Date*High + Low + Date*Low + 
                                 High*Low + DOW + Clouds + Rain + Winter, data=foodTrain)


#Main function
finalFunction = function(dayofWeek, date, high, low, conditions = "Clear") {
  #Preparing the input
  formattedDate = as.Date(date, format = "%m/%d/%y")
  dayOfYear = yday(formattedDate)
  clouds = "Clear"
  rain = FALSE
  winter = FALSE
  
  #Creating variables "clouds", "rain" and "winter" based on "conditions" parameter
  if (str_contains(conditions, "Clear")) { clouds = "Clear" }
  if (str_contains(conditions, "Partially cloudy")) { clouds = "Partially Cloudy" }
  if (str_contains(conditions, "Overcast")) { clouds = "Overcast" }
  if (str_contains(conditions, "Clear") == FALSE &
      str_contains(conditions, "Partially cloudy") == FALSE &
      str_contains(conditions, "Overcast") == FALSE) { clouds = "Other"}
  
  if (str_contains(conditions, "Rain")) { rain = TRUE }
  if (str_contains(conditions, "Snow") | 
      str_contains(conditions, "Ice") |
      str_contains(conditions, "Freezing Drizzle/Freezing Rain")) { winter = TRUE }
  
  #Creating predictions for each category
  totalPrediction = predict(quadraticModel, data.frame(Date = dayOfYear, Date2 = dayOfYear^2, High = high,
                                                       Low = low, DOW = dayofWeek, Clouds = clouds,
                                                       Rain = rain, Winter = winter))
  iceCreamPrediction = predict(iceCreamModel, data.frame(Date = dayOfYear, Date2 = dayOfYear^2, High = high,
                                                         Low = low, DOW = dayofWeek, Clouds = clouds,
                                                         Rain = rain, Winter = winter))
  foodPrediction = predict(foodModel, data.frame(Date = dayOfYear, Date2 = dayOfYear^2, High = high,
                                                 Low = low, DOW = dayofWeek, Clouds = clouds,
                                                 Rain = rain, Winter = winter))
  
  #Output is created here
  cat("For ", format(formattedDate, format = "%B %d, %Y"), ":", sep="")
  cat("\nTotal Items Sold:", round(totalPrediction))
  cat("\nFood Items Sold:", round(foodPrediction))
  cat("\nIce Cream Sold:", round(iceCreamPrediction))
  cat("\nDrinks Sold:", round(totalPrediction - (foodPrediction + iceCreamPrediction)))
}