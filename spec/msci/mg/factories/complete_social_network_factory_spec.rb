require File.join(
  File.dirname(__FILE__), '..', '..', '..', 'spec_helper.rb'
)

import msci.mg.agents.AbstractAgent
import msci.mg.factories.AgentFactory
import msci.mg.factories.CompleteSocialNetworkFactory
import msci.mg.factories.FriendshipFactory
import msci.mg.factories.SocialNetworkFactory
import msci.mg.Friendship

describe CompleteSocialNetworkFactory do
  ConcreteAgentFactory = Class.new(AgentFactory) do
    def create
      return Mockito.mock(AbstractAgent.java_class)
    end
  end
  ConcreteFriendshipFactory = Class.new(FriendshipFactory) do
    def create
      return Mockito.mock(Friendship.java_class)
    end
  end
  
  let(:agent_factory) { ConcreteAgentFactory.new }
  let(:friendship_factory) { ConcreteFriendshipFactory.new }
  
  let(:number_of_agents) { 101 }
  
  let(:factory) { 
    CompleteSocialNetworkFactory.new(
      agent_factory, 
      friendship_factory, 
      number_of_agents
    )
  }
  
  it "extends the SocialNetworkFactory class" do
    factory.should be_a_kind_of(SocialNetworkFactory)
  end
  
  describe "#create" do
    it "generates a social network containing the specified number of " + 
      "agents" do
      social_network = factory.create
      social_network.vertex_count.should == number_of_agents
    end
    
    it "generates a social network with N(N-1)/2 friendships where N is " + 
      "the number of agents" do
      social_network = factory.create
      social_network.edge_count.should == 
        (number_of_agents * (number_of_agents - 1)) / 2
    end
  end
end