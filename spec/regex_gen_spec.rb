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

    it "should return value with chars from the set" do
      RegexGen.of("[aeiou]{3}").should match("^[aeiou]{3}$")
    end

    it "should return value with chars within range" do
      RegexGen.of("[a-f]{3}").should match("^[a-f]{3}$")
    end

    it "should return value from both character set and range" do
      RegexGen.of("[0-9HAL]{3}").should match("^[0-9HAL]{3}$")
      RegexGen.of("[HAL0-9]{3}").should match("^[HAL0-9]{3}$")
    end

    it "should return value with mixed exps" do
      RegexGen.of("[0-9]{2}\\w{3}").should match("[0-9]{2}\\w{3}")
      RegexGen.of("\\w{3}[0-9]{2}").should match("\\w{3}[0-9]{2}")
      RegexGen.of("\\w{3}[0-9]{2}\\w{4}").should match("\\w{3}[0-9]{2}\\w{4}")
    end

    it "should work without a quantifier" do
      RegexGen.of("[a-z]").should match("^[a-z]$")
    end
  end
end
