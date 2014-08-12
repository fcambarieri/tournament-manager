
<!-- Nav -->
<style type="text/css" media="screen">
#settings:hover { color: #555; }
#menu { display: none; }
#arrow
{
	  width: 0;
	  height: 0;
	  border-left: 7px solid transparent;
	  border-right: 7px solid transparent;
	  border-bottom: 9px solid #F8F8F8;
  	margin-left: 15px;
}
</style>
<nav id="nav">
	<ul class="container">
		<li><g:link controller="home" action="index">${message(code: 'default.link.home.label', default: 'Home')}</g:link></li>
		<sec:ifNotLoggedIn>
			<li><a href="#contact">Contact</a></li>
			<li><g:link controller="registration" action="index">${message(code: 'default.link.signup.label', default: 'Sign up')}</g:link></li>
			<li><g:link controller="login" action="auth">${message(code: 'default.link.sigin.label', default: 'Sign in')}</g:link></li>
		</sec:ifNotLoggedIn>
		
		<sec:ifLoggedIn>
			<li><g:link controller="tournament" action="index">${message(code: 'default.link.tournament.label', default: 'Tournament')}</g:link></li>
			<li><g:link controller="categories" action="index">${message(code: 'default.link.categories.label', default: 'Categories')}</g:link></li>
			<li><g:link controller="schools" action="index">${message(code: 'default.link.schools.label', default: 'Schools')}</g:link></li>
			<li><g:link controller="participants" action="index">${message(code: 'default.link.participans.label', default: 'Participants')}</g:link></li>
			<li>
				<div class="loginMenu">
					<div id="username"><sec:username /></div>
					<div id="menu">
						<g:link controller="logout">sign out</g:link>
					</div>
				</div>
			</li>	
		</sec:ifLoggedIn>
		
		<li><g:link controller="help" action="index">${message(code: 'default.link.help.label', default: 'Help')}</g:link></li>
	</ul>
</nav>
