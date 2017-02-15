import java.util.ArrayList;
import element.Element;
import element.terrain.Obstacle;
import element.terrain.Resource;
import element.unit.ControlCenter;
import element.unit.Worker;

public class Mineiro extends Worker 
{
	private int x_ant = 0;
	private int y_ant = 0;

	// Worker's state machine
	private static final byte STATE_SEARCH 	= 0;
	private static final byte STATE_MINE 	= 1;
	private static final byte STATE_RETURN 	= 2;
	private static final byte STATE_DELIVER	= 3;

	// Store the worker's current state 
	private byte currentState;

	// Store the mine that the worker is mining
	private Resource currentMine;

	public Mineiro()
	{
		// Initial state
		this.currentState = STATE_SEARCH;
		this.currentMine = null;
	}

	public void updateAction() 
	{
		// Choose the correct action
		this.chooseAction();
	}

	private void chooseAction()
	{

		switch (this.currentState) 
		{
		case STATE_SEARCH:
			// Search mine
			Resource res = this.findMine();

			// If the mine is near from the worker, change the worker 
			// state to MINE,
			// If the mine is not near from the worker, move		
			if (res == null)
			{
				this.encontraMina();
			}
			else
			{	
				this.currentState = STATE_MINE;
				this.currentMine = res;
				this.chooseAction();
			}
			break;

		case STATE_MINE:
			// If the mine still exists
			if (this.currentMine != null)
			{
				// If the worker is full than change state to RETURN to 
				// the control center,
				// If not, continue to mine
				if (this.isFull())
				{
					this.currentState = STATE_RETURN;
					this.chooseAction();
				}
				else
				{
					this.mine(this.currentMine);
				}
			}
			else
			{
				// If the mine doesn't exist any more,
				// change the state to SEARCH
				this.currentState = STATE_SEARCH;
				this.chooseAction();
			}
			break;

		case STATE_RETURN:
			// Try to find a near control center 
			ControlCenter cc = this.findCC();

			// If there don't find, keep walking,
			// if find, change the state to DELIVER
			if (cc == null)
			{


				this.retornaCC();

			}
			else
			{				
				this.currentState = STATE_DELIVER;				
				this.chooseAction();

			}
			break;

		case STATE_DELIVER:		
			// If the agent still has resource, keep delivering,
			// If not, change the state to SEARCH
			if (!this.isEmpty())
			{
				this.deliver();
			}
			else
			{				
				this.currentState = STATE_SEARCH;				
				this.chooseAction();
			}
			break;			


		default:
			break;
		}
	}

	/**
	 * Random move
	 * Move in the map randomly.
	 */
	private void retornaCC()
	{
		ControlCenter base = mapeiaCC();

		int x_movement = 0;		
		int y_movement = 0;

		if(this.getX()>base.getX())
			x_movement += this.getX()-1;
		else
			if(this.getX()<base.getX())
				x_movement += this.getX()+1;

		if(this.getY()>base.getY())
			y_movement += this.getY()-1;
		else
			if(this.getY()<base.getY())
				y_movement += this.getY()+1;


		if(x_ant == x_movement && y_ant == y_movement)
			this.randomMove();
		else{
			this.move(x_movement, y_movement);
			x_ant = x_movement;
			y_ant = y_movement;
		}
	}

	private void encontraMina()
	{

		ArrayList<Resource> mina = mapeiaMina();

		Resource alvo = null;
		float distancia = (32*32) ;

		for(Resource mi : mina)
			if(calculaDistancia(mi,this.getX(),this.getY()) < distancia){
				distancia = calculaDistancia(mi,this.getX(),this.getY());
				alvo = mi; 
			}
		

		
		int x_movement = 0;		
		int y_movement = 0;

		if(this.getX()>alvo.getX())
			x_movement += this.getX()-1;
		else
			if(this.getX()<alvo.getX())
				x_movement += this.getX()+1;

		if(this.getY()>alvo.getY())
			y_movement += this.getY()-1;
		else
			if(this.getY()<alvo.getY())
				y_movement += this.getY()+1;


		if(x_ant == x_movement && y_ant == y_movement)
			this.randomMove();
		else{
			this.move(x_movement, y_movement);
			x_ant = x_movement;
			y_ant = y_movement;
		}
	}



	private void randomMove()
	{
		int x_movement = (int)(Math.random() * 1000) % 2;		
		int y_movement = (int)(Math.random() * 1000) % 2;

		if (Math.random() > 0.5f)
			x_movement = x_movement * -1;

		if (Math.random() > 0.5f)
			y_movement = y_movement * -1;

		this.move(this.getX() + x_movement, this.getY() + y_movement);

		x_ant = this.getX() + x_movement;
		y_ant = this.getY() + y_movement;
	}



	/**
	 * Find a resource (mine) near from the worker.
	 * @return
	 */
	private Resource findMine()
	{
		Resource result = null;
		int x = this.getX();
		int y = this.getY();

		for(int i = x-1; i <= x+1; i++)
		{
			for(int j = y-1; j <= y+1; j++)
			{
				Element element = this.getElementAt(i, j);
				if (element instanceof Resource)
				{
					result = (Resource)element;
					break;
				}
			}
		}	

		return result;
	}


	private ArrayList<Resource> mapeiaMina()
	{

		ArrayList<Resource> result = new ArrayList<Resource>();


		for(int i = 0; i <32; i++)
		{
			for(int j = 0; j <32; j++)
			{
				Element element = this.getElementAt(i, j);
				if (element instanceof Resource)
				{
					result.add((Resource)element);

				}
			}
		}	


		return result;
	}
	
	private ArrayList<Obstacle> mapeiaObstaculo()
	{

		ArrayList<Obstacle> result = new ArrayList<>();


		for(int i = 0; i <32; i++)
		{
			for(int j = 0; j <32; j++)
			{
				Obstacle element = (Obstacle) this.getElementAt(i, j);
				if (element instanceof Obstacle)
				{
					result.add((Obstacle)element);

				}
			}
		}	


		return result;
	}
	

	private float calculaDistancia(Resource mi, int x, int y)
	{

		float result = 0;
		int dadoA = (mi.getX()-x)*(mi.getX()-x);	
		int dadoB = (mi.getY()-y)*(mi.getY()-y);
		
		result = (float) Math.sqrt(dadoA+dadoB);
		
		return result;
	}






	/**
	 * Find a control center (cc) near from the worker.
	 * @return
	 */
	private ControlCenter findCC()
	{
		ControlCenter result = null;
		int x = this.getX();
		int y = this.getY();

		for(int i = x-1; i <= x+1; i++)
		{
			for(int j = y-1; j <= y+1; j++)
			{
				Element element = this.getElementAt(i, j);
				if (element instanceof ControlCenter)
				{
					result = (ControlCenter)element;
					break;
				}
			}
		}	

		return result;
	}

	private ControlCenter mapeiaCC()
	{
		ControlCenter result = null;


		for(int i = 0; i < 32; i++)
		{
			for(int j = 0; j < 32; j++)
			{
				Element element = this.getElementAt(i, j);
				if (element instanceof ControlCenter)
				{
					result = (ControlCenter)element;
					break;
				}
			}
		}	

		return result;
	}


	/**
	 * Verify if the worker is full.
	 * @return
	 */
	private boolean isFull()
	{
		boolean result = true;

		
		if (this.currentMine.getQuantity() > 0 && this.getResources() < this.getTotalCapacity())
		{
			result = false;
		}


		return result;
	}

	/**
	 * Verify if the worker is empty.
	 * @return
	 */	
	private boolean isEmpty()
	{
		boolean result = false;

		if (this.getResources() == 0)
		{
			result = true;
		}

		return result;
	}	


}