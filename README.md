TopicDetector_NBM
=================

NaiveBaseModel

Deciding the topic of an article, blog or news by category the words in this topic. 
Words are already replaced by integer to make recognizition easier. 
Using training data set to learn and test data set to test.

This is a Naive Base model. 
The main idea is to create a Bays net work with 2 level: root node is the topic while the child node is Pr(word|topic)
After learning, run variable elimition to decide the topic.
