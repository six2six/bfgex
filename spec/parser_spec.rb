require 'spec_helper'

describe Parser do
  describe ".parse" do
    it "should return a sexp for a non-empty string" do
      Parser.parse("abc").should be_instance_of(Sexp)
    end

    it "should return nil for an empty string" do
      Parser.parse("").should be_nil
    end

    it "should return literal sexp" do
      Parser.parse('x').to_s.should eql("(LITERAL,x)")
    end

    it "should return a possessive quantify sexp" do
      Parser.parse('a*').to_s.should eql("(QUANTIFY,(LITERAL,a),*)")
    end

    it "should return a intersection with range quantifier word and digit with number quantifier" do
      Parser.parse('(\\w{5,6})|(\\d{4})').to_s.should eql("(INTERSECTION,(QUANTIFY,(RANDOM,WORD),(5..6)),(QUANTIFY,(RANDOM,DIGIT),4))")
    end

    it "should return a union with word possessive quantify and digit with possessive quantifier" do
      Parser.parse('(\\w*)(\\d*)').to_s.should eql("(UNION,(QUANTIFY,(RANDOM,WORD),*),(QUANTIFY,(RANDOM,DIGIT),*))")
    end

    it "should return literals and quantifier word" do
      Parser.parse('ab\\w+').to_s.should eql("(UNION,(LITERAL,a),(LITERAL,b),(QUANTIFY,(RANDOM,WORD),+))")
    end
  end

end

