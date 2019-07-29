# serenirtyEmailSender

Test for sending letters to different recipients, with different titles and attachments


Tests written using Java + Maven + JUnit + Serenity-BDD

**Run tests with maven**

`mvn  verify`

**Run tests with parameters**

`mvn  -Ddriver=<browser>-DtestProps=<path_to_file>  verify`

For example:

`mvn  -Ddriver=chrome -DtestProps=prod.properties  verify`

The following are test parameters(all of then are optional):

| Property | Description  | Default value  |
| ------- | --- |  :---:  |
| driver | which browser to use | firefox |
| testProps | login and test data file | prod.properties |
| properties | serenity property file | serenity.properties |


**Test properties file**
  
File testProps should contain the following:

- `login` - login name to mail.ru account, from which letters will be sent
- `password` - password to mail.ru account, from which letters will be sent
- `lettersData` - path to the json file where the information about the letters will be stored (recipient, subject, etc.)

The example of testProps file:

`login = serenityTest@mail.ru
 password = selenium!23
  lettersData=lettersData.json`

**Test data file**

The file with the test data data should contain information about the sent letters:
- `to `- recipient's email address
- `subject` - subject of the letter
- `text` - letter text
- `files` - list of file paths to attach to the letter

The example of test data file:
```json
[
  {
    "to": "elenka220890@mail.ru",
    "subject": "Selenium Test data provider",
    "text": "Hi!",
    "files":[
      ".\\pics\\1.txt",
      ".\\pics\\2.txt"
    ]
  },
  {
    "to": "elenka220890@mail.ru",
    "subject": "Selenium Test data provider 22",
    "text": "Hi!2"
  }
]
```

