#Correlation
cor(train_subset$target, train_subset$months_active) #-0.00500464
cor(train_subset$target, ht_subset3$purchase_amount) #-0.008211368
cor(train_subset$target, ht_subset3$installments) #-0.001617116
cor(train_subset$target, ht_subset3$month_lag) #-0.00136912

#plotting ht_subset3
plot(ht_subset3$month_lag, ht_subset3$purchase_amount, `main`= "HT Test", 
     `xlab` = "Purchase Amount", ylab = "Month Lag")
abline(lm(ht_subset3$purchase_amount ~ ht_subset3$month_lag))

#plotting train_subset
plot(train_subset$months_active, train_subset$target, `main`= "# Months Active vs. Loyalty Score", 
     `xlab` = "# Months Active", ylab = "Loyalty Score")
abline(lm(train_subset$target ~ train_subset$months_active))

#plotting across both tables
plot(ht_subset3$month_lag, train_subset$target, `main`= "Month Lag vs. Loyalty Score", 
     `xlab` = "Month Lag", ylab = "Loyalty Score")
abline(lm(train_subset$target ~ ht_subset3$month_lag))

#First_Month_Active Conversion
final_list = c()

for (x in (1:nrow(train))) {
  yearTest = substr(train$first_active_month[x], 4, 4)
  monthTest = substr(train$first_active_month[x], 6, 7)
  month = as.integer(monthTest)
  year = as.integer(yearTest)
  answer = 0
  if (month == 2) {
   answer = (8 - year)*12
  }
  else if (month == 1) {
   answer = (8 - year)*12 + 1
  }
  else {
   answer = (7 - year)*12 + (14-month)
  }
  final_list[x] = answer
}
train$months_active = final_list

#Removing outliers from train
outliers = c()
out_index = 1
for (x in (1:nrow(train))) {
  if (train$target[x] < -25) {
    outliers[out_index] = x
    out_index = out_index + 1
  }
}
View(outliers)
train2 = train[-outliers,]

#Creating subset of historical_transactions (outliers not yet removed)
#Subset is created first so that historical_transactions is easier to work with
ht_subset = historical_transactions[1:60000,]

#Creating subset of train2 (outliers already removed)
train_subset = train2[1:50000,]

#Removing outliers from ht_subset
outliers = c()
outliers2 = c()
out_index = 1
for (x in (1:nrow(ht_subset))) {
  if (ht_subset$purchase_amount[x] > 4) {
    outliers[out_index] = x
    out_index = out_index + 1
  }
}

ht_subset2 = ht_subset[-outliers,]
ht_subset3 = ht_subset2[1:50000,]