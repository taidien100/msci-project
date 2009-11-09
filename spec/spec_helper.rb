$LOAD_PATH.unshift File.expand_path(
  File.join(
    File.dirname(__FILE__), '..', 'dist'
  )
) unless
  $LOAD_PATH.include?(File.join(File.dirname(__FILE__), '..', 'dist')) ||
  $LOAD_PATH.include?(File.expand_path(
    File.join(File.dirname(__FILE__), '..', 'dist')
  )
)

require 'rubygems'
require 'java'
require 'spec'

require 'MSci_Project_Code.jar'