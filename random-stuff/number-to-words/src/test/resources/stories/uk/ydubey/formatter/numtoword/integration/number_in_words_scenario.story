Scenario: the number is correctly formatted to words

When the number is <number>
Then in-words it should be <inWords>

Examples:
|number|inWords|
|0||
|99|ninety nine|
|100|one hundred|
|171|one hundred and seventy one|
|999|nine hundred and ninety nine|
|2345|two thousand three hundred and forty five|
|53936|fifty three thousand nine hundred and thirty six|
|973920|nine hundred and seventy three thousand nine hundred and twenty|
|9305734|nine million three hundred and five thousand seven hundred and thirty four|