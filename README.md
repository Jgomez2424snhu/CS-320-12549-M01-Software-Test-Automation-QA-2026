# CS-320-12549-M01-Software-Test-Automation-QA-2026
## This is the Module 8 submission for CS 320
## Software Testing, Automation, and Quality Assurance

This section showcases work from my software testing course, where I built and tested a set of Java backend services (Contact, Task, and Appointment) for a mobile app, writing both the production code and the JUnit 5 test suites that verify it against the stated requirements.

**Artifacts included:**
- `Contact.java`, `ContactService.java` — the Contact class and its in-memory service layer (add, delete, update, and look up a contact by ID)
- `ContactTest.java`, `ContactServiceTest.java` — the JUnit 5 test suites covering every field rule, boundary value, and service operation
- `Project Two Summary and Reflections Report.docx` — a summary of my testing approach across all three services, defended with real JaCoCo coverage numbers, plus a reflection on the testing techniques I used and did not use, and on caution, bias, and discipline as a tester

**Reflection**

*How can I ensure that my code, program, or software is functional and secure?*

I treat every stated requirement as a rule to test from both directions: an acceptance test confirming a valid value works, and a rejection test confirming an invalid one is refused, tested right at the boundary rather than at a safe middle value. That habit is what caught the real gap in my own code this semester: a manual trace of my own tests looked complete, but it missed that the null check in each service's add method was never actually exercised, since every existing test happened to pass in a real object first. Running a real coverage tool against the compiled code, instead of trusting my own read of it, is what surfaced that gap and let me close it. I also build functionality defensively rather than assuming good input: regex-validating the phone field instead of only checking its length, keeping ID fields final so they cannot be changed after creation, and defensively copying mutable fields like a java.util.Date so a caller cannot alter an object's state after the fact. A separate security course reinforced the same instinct from another angle, scanning dependencies for known vulnerabilities and verifying data with checksums, but the core principle is the same one testing taught me: validate everything you did not generate yourself, and verify your own assumptions with a tool rather than trust alone.

*How do I interpret user needs and incorporate them into a program?*

I start from the written requirements and turn every stated rule directly into a test before I trust that my code satisfies it, which forces me to read the requirements precisely instead of assuming I already know what they mean. A contact's 10-character ID limit, a phone number's exact 10-digit format, and an appointment date that cannot fall in the past are not design choices I made up; they come straight from what the customer specified, and my tests exist to prove the code honors those specifics rather than a looser version of them. Where the requirements are genuinely silent, like whether a field can be updated later, I treat that as a question to confirm rather than guess, since the Appointment service's lack of an update method was itself a requirement, not an oversight.

*How do I approach designing software?*

I design for a single source of truth per rule: each class's constructor calls the same setters a later update would use, so there is one place to get a rule right instead of a copy of it in two places that can drift apart. I lean on immutability and defensive copying wherever a value should not change after creation, and I use a simple, direct data structure, a HashMap keyed by a unique ID, instead of something more complex than the requirements actually call for. Just as importantly, I write the tests alongside the code rather than after it, and I reach for a real tool, like a coverage report, whenever one is available instead of trusting my own intuition about whether the code is correct.
