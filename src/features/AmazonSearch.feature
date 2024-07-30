Feature: Amazon Web Functionality

@SmokeTest

Scenario: Amazon Website Search Functionality
Given launch Browser and Navigate to Amazon website
When User search something
Then list all related Search appears
