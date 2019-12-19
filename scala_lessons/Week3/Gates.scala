package Week3



abstract class Gates extends Simulation {
  def InverterDelay: Int
  def AndGateDelay:Int
  def OrGateDelay:Int

  class Wire{
   private var sigVal = false
   private  var actions:List[Action] = List()

   def getSignal = sigVal

   def setSignal(s:Boolean) =
    if (s!=sigVal){
     sigVal = s
     actions foreach(_())
    }

   def addAction(a:Action) = {
    actions = a :: actions
    a()
   }
  }

 def inverter(input:Wire,output: Wire):Unit = {
  def invertAction(): Unit = {
   val inputSig = input.getSignal
   afterDelay(InverterDelay)
   {
    output setSignal !inputSig
   } // after delay bracket
  }
  input addAction invertAction
 }

 def andGate(in1:Wire,in2:Wire,output: Wire) = {
  def andAction() = {
   val in1Sig = in1.getSignal
   val in2Sig = in2.getSignal
   afterDelay(AndGateDelay)
   {
    output setSignal(in1Sig & in2Sig)
   }
  }
  in1 addAction andAction
  in2 addAction andAction
 }
 def orGate(in1:Wire,in2:Wire,output: Wire) = {
  def orAction() = {
   val in1Sig = in1.getSignal
   val in2Sig = in2.getSignal
   afterDelay(AndGateDelay)
   {
    output setSignal(in1Sig | in2Sig)
   }
  }
  in1 addAction orAction
  in2 addAction orAction
 }

 def probe(name:String, wire:Wire):Unit =  {
  def probAction():Unit ={
   println(name + " " + currentTime + " New - Value" + wire.getSignal)
  }
  wire addAction probAction
 }

}
