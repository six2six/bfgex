require 'spec_helper'

describe Gen do
  describe ".of" do

    it "should return the pattern for literal" do
      Gen.of("x{3}").should match("x{3}")
      Gen.of("a{3,8}").should match("a{3,8}")
    end

    it "should return the pattern for number quantifier" do
      Gen.of("\\w{6}").should match("\\w{6}")
      Gen.of("\\d{3,8}").should match("\\d{3,8}")
    end

    it "should return value for possessive quantifier" do
      Gen.of("\\w+").should match("\\w+")
      Gen.of("\\d+").should match("\\d+")
    end
  end
end
