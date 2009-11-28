require File.join(File.dirname(__FILE__), '..', '..', '..', 'spec_helper.rb')

describe MSciProject::MinorityGame::LearningAgent do
  let(:package) { MSciProject::MinorityGame }
  let(:klass) { package::LearningAgent }
  let(:empty_strategy_collection) { package::StrategyCollection.new }
  let(:learning_agent_instance) { klass.new(empty_strategy_collection) }
  
  it "extends the AbstractAgent class" do
    learning_agent_instance.should be_a_kind_of(package::AbstractAgent)
  end
end