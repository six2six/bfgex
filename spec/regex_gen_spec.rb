require 'spec_helper'

describe RegexGen do
  describe ".of" do

    it "should return the pattern for literal" do
      RegexGen.of("x{3}").should match("x{3}")
      RegexGen.of("a{3,8}").should match("a{3,8}")
    end

    it "should return the pattern for number quantifier" do
      RegexGen.of("\\w{6}").should match("\\w{6}")
      RegexGen.of("\\d{3,8}").should match("\\d{3,8}")
    end

    it "should return value for possessive quantifier" do
      RegexGen.of("\\w+").should match("\\w+")
      RegexGen.of("\\d+").should match("\\d+")
    end

    it "should return union of literal values" do
      RegexGen.of("abc").should match("abc")
    end

    it "should return intersection of values" do
      RegexGen.of("(\\w{5,7})|(\\d+)").should match("(\\w{5,7})|(\\d+)")
    end

  end
end
