export default function Input2({ label, type, value, onChange}) {
    return (
        <div className="w-1/4 px-2 mb-4 ">
            <label>{label}</label>
            <input
                type={type}
                value={value}
                onChange = {onChange}
                className=" w-full px-3 py-2 border border-gray-300 rounded focus:outline-none"
            />
        </div>
    )
}