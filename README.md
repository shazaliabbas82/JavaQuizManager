# JavaQuizManager
This project is proposed to students in order to evaluate their skills for the fundamental period of Java Programming and UML.  This goal is achieved through the realization of a console application (+ GUI as possible bonus), which aims at managing digital quiz preparation and execution.

Specifications

The usual problem while preparing and running an evaluation, is to :

Constitute an appropriate evaluation corresponding of the required level Reuse former questions Organize sample evaluations Correct automatically the MCQ questions.

Types of question

MCQ Questions Open Questions Associative Questions

The MCQ questions The MCQ questions are composed of a question text and a set of possible choices, each choice can be right or wrong. It can also be interesting to add a extra content, like some code extract, some picture or some other kind of media (video, music etc.).

The Open Questions The open questions are composed only by a question, and some hints, additionaly they can be completed by a exta media content.

Associative questions The associative questions are questions where it necessary to assign some propositions to some descriptions, like in the following.

Common questions attributes Each question has a some extra attributes to describe the topic (tag) and the difficulty of the question. Those two fields help to balance the overall exam complexity, and the topics coverage. Those attributes can be taken in account for automatic exam assembly.

Main features The produced application should be a console application. The scope that you should cover by this application is to :

be able to operate CRUD on Open Questions and MCQ Questions (questions and valid answers are stored in a database or an XML file or a JSON file) be able to search questions based on topics be able to assemble automatically a quiz (a quiz is a set of questions) that gathers all the questions covering a given list of topics. export this quiz under a plain text format run the evaluation and provide the automatic mark in the end of this execution
