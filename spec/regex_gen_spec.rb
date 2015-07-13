require 'spec_helper'

describe RegexGen do
  describe ".of" do

    it "should return the pattern for literal" do
      expect(RegexGen.of("x{3}")).to match("x{3}")
      expect(RegexGen.of("a{3,8}")).to match("a{3,8}")
    end

    it "should return the pattern for number quantifier" do
      expect(RegexGen.of("\\w{6}")).to match("\\w{6}")
      expect(RegexGen.of("\\d{3,8}")).to match("\\d{3,8}")
    end

    it "should return value for possessive quantifier" do
      expect(RegexGen.of("\\w+")).to match("\\w+")
      expect(RegexGen.of("\\d+")).to match("\\d+")
    end

    it "should return union of literal values" do
      expect(RegexGen.of("abc")).to match("abc")
    end

    it "should return intersection of values" do
      expect(RegexGen.of("(\\w{5,7})|(\\d+)")).to match("(\\w{5,7})|(\\d+)")
    end

    it "should return value with chars from the set" do
      expect(RegexGen.of("[aeiou]{3}")).to match("^[aeiou]{3}$")
    end

    it "should return value with chars within range" do
      expect(RegexGen.of("[a-f]{3}")).to match("^[a-f]{3}$")
    end

    it "should return exact number of requested characters in range" do
      expect(RegexGen.of("[a-c]{100}")).to match("^[a-c]{100}$")
    end

    it "should return value from both character set and range" do
      expect(RegexGen.of("[0-9HAL]{3}")).to match("^[0-9HAL]{3}$")
      expect(RegexGen.of("[HAL0-9]{3}")).to match("^[HAL0-9]{3}$")
    end

    it "should return value with mixed exps" do
      expect(RegexGen.of("[0-9]{2}\\w{3}")).to match("[0-9]{2}\\w{3}")
      expect(RegexGen.of("\\w{3}[0-9]{2}")).to match("\\w{3}[0-9]{2}")
      expect(RegexGen.of("\\w{3}[0-9]{2}\\w{4}")).to match("\\w{3}[0-9]{2}\\w{4}")
    end

    it "should work without a quantifier" do
      expect(RegexGen.of("[a-z]")).to match("^[a-z]$")
    end
  end
end
