[![Build Status](https://travis-ci.org/yeison/SetSolver.svg?branch=master)](https://travis-ci.org/yeison/SetSolver)

See src/test/java/set/SetSolverTest.java for examples of how to run the SetSolver


Alternatively, run the SetSolver from the command line.  In order to do so, unzip SetSolver-1.0.zip

In the unzipped folder, run SetSolver as follows:

```
bin/SetSolver set_input_1
```

In order to compute other sets, it's necessary to create new input input files.  The format is the following:

The first line must indicate the number of dimensions and the number of values available per dimension, separated by a comma.

'#dims,#values'

Every subsequent line specifies the card values, comma-separated.

For example, a card with all 0 values can be specified as:

0,0,0,0

Refer to the file 'set_input_1' for reference.