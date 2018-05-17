package daoInterfaces;

public interface IDAOFactory {

	public IInstitutDAO getInstitutDAO();

	public IFacultyDAO getFacultyDAO();

	public ISpecialtyDAO getSpecialtyDAO();

	public IGroupDAO getGroupDAO();

	public IStudentDAO getStudentDAO();

	public IMarkDAO getMarkDAO();

	public ISubjectDAO getSubjectDAO();

	public ISubjectNameDAO getSubjectNameDAO();

	public ITeacherDAO getTeacherDAO();

	public IKafedraDAO getKafedraDAO();
}
