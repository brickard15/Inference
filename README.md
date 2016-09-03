[![Build Status](https://travis-ci.org/MSUCSIS/Inference.svg?branch=master)](https://travis-ci.org/MSUCSIS/Inference)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/614c3b08bf5240d5b3dcd7fa74253e69)](https://www.codacy.com/app/pwright4/Inference_2?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=msucsis/Inference&amp;utm_campaign=Badge_Grade)
# Inference Library

This is a simple library for analyzing samples of text and assigning them a list of possible data types. Currently, the library only supports the testing of a single text sample at a time, but ultimately it will support the following:

* Analyzing a collection of samples and providing estimates for the likelihood of each candidate type
* Analyzing documents and annotating them with type information
* Analyzing collections of documents and annotating them with type information and estimates for the likelihood of each candidate type
* Analyzing streaming data to handle very large collections of data
* Generating code which can deserialize documents while providing more type safety than using, e.g., maps of strings

